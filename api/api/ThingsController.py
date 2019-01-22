import base64
from app import app
import json
from flask import g, request, abort
from .utils import json_response, JSON_MIME_TYPE
import  DBConnector
import datetime
import firebase_admin
from firebase_admin import credentials, firestore, storage
import os
import glob
from PIL import Image
import random


@app.route('/things/<int:id>', methods=['DELETE'])
def thing_delete(id):
    params = {'id': id}
    query = 'SELECT count(*) FROM ITEM WHERE ID = :id'
    cursor = g.db.execute(query, params)

    # Check if book exists
    if cursor.fetchone()[0] == 0:
        # Doesn't exist. Return 404.
        abort(404)

    # Delete it
    delete_query = 'DELETE FROM ITEM WHERE ID = :id'
    g.db.execute(delete_query, {'id': id})
    g.db.commit()

    return json_response(status=204)


@app.route('/things', methods=['PATCH'])
def thing_update():
    if request.content_type != JSON_MIME_TYPE:
        error = json.dumps({'error': 'Invalid Content Type'})
        return json_response(error, 400)

    data = request.json
    if not all(
            [data.get('id'), data.get('user_id'), data.get('name'), data.get('description'), data.get('location'),
             data.get('contact'), data.get('found_date'), data.get('add_date'), data.get('thumbnail'),
             data.get('is_returned')]):
        error = json.dumps({'error': 'Missing field/s (id, user_id, name, description, '
                                     'location, contact, found_date, thumbnail, add_date, is_returned)'})

        return json_response(error, 400)

    params = [{
        'id': data['id'],
        'user_id': data['user_id'],
        'namee': data['name'],
        'description': data['description'],
        'location': data['location'],
        'contact': data['contact'],
        'found_date': data['found_date'],
        'is_returned': data['is_returned'],
    }]

    query = (
        'UPDATE ITEM SET USER_ID = :user_id, NAME = :namee, DESCRIPTION = :description,'
        ' LOCATION = :location, CONTACT = :contact, IS_RETURNED = :is_returned '
        'WHERE ID = :id;')

    g.db.execute(query, params[0])
    g.db.commit()

    params = {
        'id': data['id'],
    }

    cursor = g.db.execute('SELECT * FROM ITEM WHERE ID = :id;', params)

    items = [{
        'id': row[0],
        'user_id': row[1],
        'name': row[2],
        'description': row[3],
        'location': row[4],
        'contact': row[5],
        'found_date': row[6],
        'add_date': row[7],
        'thumbnail': row[8],
        'is_returned': row[9],
    } for row in cursor.fetchall()]

    return json_response(json.dumps(items), status=201)


@app.route('/things', methods=['POST'])
def thing_create():
    if request.content_type != JSON_MIME_TYPE:
        error = json.dumps({'error': 'Invalid Content Type'})
        return json_response(error, 400)

    data = request.json
    if not all([data.get('user_id'), data.get('name'), data.get('description'), data.get('location'), data.get('contact'), data.get('thumbnail')]):
        error = json.dumps({'error': 'Missing field/s (user_id, name, description, location, contact, thumbnail)'})
        return json_response(error, 400)

    rand = random.randint(0, 9999)
    convert_and_save(data.get('thumbnail'), data.get('name')+rand.__str__())

    name = ('{0}.png'.format(data.get('name')+rand.__str__() ))
    nameThumbnail = ('{0}thumbnail.png'.format(data.get('name')+rand.__str__()))

    bucket = storage.bucket()
    blob = bucket.blob(name)

    with open(name, 'rb') as my_file:
        blob.upload_from_file(my_file)

    im = Image.open(name)
    im.thumbnail((128, 128), Image.ANTIALIAS)
    im.save(nameThumbnail, "PNG")

    blob = bucket.blob(nameThumbnail)

    with open(nameThumbnail, 'rb') as my_file:
        blob.upload_from_file(my_file)

    uploadedthumbBlob = bucket.get_blob(nameThumbnail)
    uploadedthumbBlob.make_public()

    uploadedBlob = bucket.get_blob(name)
    uploadedBlob.make_public()
    os.remove(name)
    os.remove(nameThumbnail)

    params = [{
        'user_id': data['user_id'],
        'namee': data['name'],
        'description': data['description'],
        'location': data['location'],
        'contact': data['contact'],
        'add_date': datetime.datetime.now(),
        'photo': uploadedBlob.public_url,
        'thumbnail' : uploadedthumbBlob.public_url,
        "returned": 0
    }]

    query = ('INSERT INTO ITEM ("USER_ID", "NAME", "DESCRIPTION", "LOCATION", '
             '"CONTACT", "ADD_DATE", "PHOTO", "IS_RETURNED", "THUMBNAIL") '
             'VALUES (:user_id, :namee, :description, :location, :contact, :add_date, :photo, :returned , :thumbnail);')

    g.db.execute(query, params[0])
    g.db.commit()

    cursor = g.db.execute('SELECT * FROM ITEM ORDER BY ID DESC LIMIT 1;')

    items = [{
        'id': row[0],
        'user_id': row[1],
        'name': row[2],
        'description': row[3],
        'location': row[4],
        'contact': row[5],
        'found_date': row[6],
        'add_date': row[7],
        'thumbnail': row[8],
        'is_returned': row[9],
        'photo': row[10]
    } for row in cursor.fetchall()]

    return json_response(json.dumps(items), status=201)


@app.route('/things/name=<string:name>')
def getByName(name):

    params = {
        'name': '%'+name+'%',
    }

    cursor = g.db.execute('SELECT * FROM ITEM WHERE NAME LIKE :name;', params)

    items = [{
        'id': row[0],
        'user_id': row[1],
        'name': row[2],
        'description': row[3],
        'location': row[4],
        'contact': row[5],
        'found_date': row[6],
        'add_date': row[7],
        'thumbnail': row[8],
        'is_returned': row[9],
    } for row in cursor.fetchall()]

    return json_response(json.dumps(items))


@app.route('/things/key=<string:key>')
def getByKeyworld(key):

    params = {
        'key': '%'+key+'%',
    }

    cursor = g.db.execute('SELECT * FROM ITEM WHERE LOCATION LIKE :key OR NAME LIKE :key;', params)

    items = [{
        'id': row[0],
        'user_id': row[1],
        'name': row[2],
        'description': row[3],
        'location': row[4],
        'contact': row[5],
        'found_date': row[6],
        'add_date': row[7],
        'thumbnail': row[8],
        'is_returned': row[9],
    } for row in cursor.fetchall()]

    return json_response(json.dumps(items))



@app.route('/things/city=<string:city>')
def getByCity(city):

    params = {
        'city': '%'+city+'%',
    }

    cursor = g.db.execute('SELECT * FROM ITEM WHERE LOCATION LIKE :city;', params)

    items = [{
        'id': row[0],
        'user_id': row[1],
        'name': row[2],
        'description': row[3],
        'location': row[4],
        'contact': row[5],
        'found_date': row[6],
        'add_date': row[7],
        'thumbnail': row[8],
        'is_returned': row[9],
    } for row in cursor.fetchall()]

    return json_response(json.dumps(items))


@app.route('/things/<int:id>')
def getById(id):

    params = {
        'id': id,
    }

    cursor = g.db.execute('SELECT * FROM ITEM WHERE id = :id;',params)

    items = [{
        'id': row[0],
        'user_id': row[1],
        'name': row[2],
        'description': row[3],
        'location': row[4],
        'contact': row[5],
        'found_date': row[6],
        'add_date': row[7],
        'thumbnail': row[8],
        'is_returned': row[9],
    } for row in cursor.fetchall()]

    return json_response(json.dumps(items))


@app.route('/things/', defaults={'id': None})
def getAll(id):

    cursor = g.db.execute('SELECT * FROM ITEM;')

    items = [{
        'id': row[0],
        'user_id': row[1],
        'name': row[2],
        'description': row[3],
        'location': row[4],
        'contact': row[5],
        'found_date': row[6],
        'add_date': row[7],
        'thumbnail': row[8],
        'is_returned': row[9],
    } for row in cursor.fetchall()]

    return json_response(json.dumps(items))


@app.route('/things/page/<int:count>&<int:page>')
def getAllPagined(count, page):

    startPoint = count * page;

    params = {
        'startPoint': startPoint,
        'limit': count
    }

    cursor = g.db.execute('SELECT * FROM ITEM ORDER BY ID ASC LIMIT :startPoint , :limit', params)

    items = [{
        'id': row[0],
        'user_id': row[1],
        'name': row[2],
        'description': row[3],
        'location': row[4],
        'contact': row[5],
        'found_date': row[6],
        'add_date': row[7],
        'thumbnail': row[8],
        'is_returned': row[9],
    } for row in cursor.fetchall()]

    return json_response(json.dumps(items))


def convert_and_save(b64_string,name):
        with open("{0}.png".format(name), "wb") as fh:
            fh.write(base64.decodebytes(b64_string.encode()))
