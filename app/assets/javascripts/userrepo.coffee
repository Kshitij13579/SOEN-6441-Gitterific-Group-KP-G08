$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onopen = (event) ->
  	ws.send(JSON.stringify({keyword: window.location.pathname.split("/").pop()}))
  ws.onmessage = (event) ->
    repo = JSON.parse event.data
    row=$('<tr/>')
    row.append $('<td/>').append repo.login
    row.append $('<td/>').append repo.name
    row.append $('<td/>').append $('<a href="/repository/'+repo.login+'/'+repo.name+'">'+repo.reponame+'</a>')
    $('#repo').append row