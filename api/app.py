import os
from flask import Flask
from flask_cors import CORS
from firebase_admin import credentials, firestore, storage
import firebase_admin

app = Flask(__name__)
cors = CORS(app, resources={r"/api/*": {"origins": "*"}})
cred = credentials.Certificate('ps.json')
firebase_admin.initialize_app(cred, {
    'storageBucket': 'sharemoments-41f09.appspot.com'
})


@app.route('/hello')
def hello_world():
    return 'Hello World!'

from api import ThingsController

if __name__ == '__main__':
    app.run()


@app.errorhandler(404)
def not_found(e):
    return '', 404
