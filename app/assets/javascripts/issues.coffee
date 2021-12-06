$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onopen = (event) ->
  	event.preventDefault()
  	url=$(location).attr('href')
  	token=url.split('/')
  	user=token[4]
  	repository=token[5]
  	$('#title').text(repository)
  	ws.send(JSON.stringify({user: user, repository: repository}))
  ws.onmessage = (event) ->
    issues = JSON.parse event.data
    #titleTest=issues.titles
    $('#titleList').empty()
   	for titleName in issues.titles
  	    $('#titleList').append $('<li/>').text(titleName)
  	row1=$('<tr>')
  	for x in issues.words
  		row1.append $('<td style="border:1px solid black" />').append 'Hello'
  	$('#issueStat').append row1