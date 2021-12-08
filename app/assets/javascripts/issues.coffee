$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onopen = (event) ->
  	event.preventDefault()
  	url=$(location).attr('href')
  	token=url.split('/')
  	user=token[4]
  	repository=token[5]
  	$('#title').text("Issue Statistics for "+repository+" Repository")
  	ws.send(JSON.stringify({user: user, repository: repository}))
  ws.onmessage = (event) ->
  	repo = JSON.parse event.data
  	$('#titleList').empty()
  	$('#issueStat').empty()
  	$('#IssueList').text("List of Issue Titles")
  	for titleName in repo.titles
  		$('#titleList').append $('<li/>').text(titleName)
  	$('#titleStat').text('Unique Words and Count')
  	for x in [0..repo.words.length-1]
  		row1=$('<tr>')
  		row1.append $('<td style="border:1px solid black" />').append repo.words[x]
  		row1.append $('<td style="border:1px solid black"/>').append repo.count[x]
  		row1.append $('<tr />')
    $('#issueStat').append row1