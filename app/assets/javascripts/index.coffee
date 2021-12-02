$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onmessage = (event) ->
    repo = JSON.parse event.data
    row=$('<tr/>')
    row.append $('<td/>').append $('<a href="/users/'+repo.login+'">'+repo.login+'</a>')
    row.append $('<td/>').append $('<a href="/repository/'+repo.login+'/'+repo.name+'">'+repo.name+'</a>')
    row.append $('<td/>').append $('<a href="/search/'+repo.login+'/'+repo.name+'/issues">'+'click here'+'</a>')
    row.append $('<td/>').append "Topic"
    row.append $('<td/>').append $('<a href="/search/'+repo.login+'/'+repo.name+'/commits">'+'click here'+'</a>')
    $('#repo').append row
    
  $("#addsymbolform").submit (event) ->
    event.preventDefault()
    # send the message to watch the stock
    ws.send(JSON.stringify({keyword: $("#addsymboltext").val()}))
    # reset the form
    $("#addsymboltext").val("")