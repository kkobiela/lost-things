import sqlite3
from app import app
from  flask import Flask, request, g


@app.before_request
def before_request():
    g.db = sqlite3.connect(app.config['LostThingsDb.db'])
