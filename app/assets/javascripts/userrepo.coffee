$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onmessage = (event) ->
    repo = JSON.parse event.data
    row=$('<tr/>')
    row.append $('<td/>').append $(repo.login)
    row.append $('<td/>').append $(repo.name)
    row.append $('<td/>').append $('<a href="/repository/'+repo.login+'/'+repo.reponame+'">'+repo.reponame+'</a>')
    $('#repo').append row