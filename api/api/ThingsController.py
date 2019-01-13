from app import  app


@app.route('/book/<int:id>')
def get(id):
    return "get"

#  def post(self):
#     logger.debug("Post")
#     return {"Post": "Post"}, 200
#
# def put(self):
#     logger.debug("Put")
#     return {"Put": "Put"}, 200
#
# def delete(self):
#     logger.debug("Delete")
#     return {"Delete": "Delete"}, 200
