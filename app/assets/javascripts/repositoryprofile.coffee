$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onopen = (event) ->
  	event.preventDefault()
  	url = $(location).attr('href')
  	token = url.split('/')
  	repository = token[5]
  	user = token[4]
  	ws.send(JSON.stringify({user: user, repository: repository}))
  ws.onmessage = (event) ->
  	repo = JSON.parse event.data
  	row=$('<tr/>')
  	row.append $('<td/>').append repo.login
  	row.append $('<td/>').append repo.name
  	row.append $('<td/>').append repo.node_id
  	row.append $('<td/>').append repo.avatar_url
  	row.append $('<td/>').append repo.open_issues
  	$('#repoprofile').append row
  	for x in [0..repo.issue_number.length-1]
  		row1=$('<tr>')
  		row1.append $('<td style="border:1px solid black" />').append repo.issue_title[x]
  		row1.append $('<td style="border:1px solid black"/>').append repo.issue_number[x]
  		row1.append $('<td style="border:1px solid black"/>').append repo.state[x]
  		row1.append $('<td style="border:1px solid black"/>').append repo.created_at[x]
  		row1.append $('<td style="border:1px solid black"/>').append repo.updated_at[x]
  		row1.append $('<tr />')
    $('#repository_issues').append row1
  	for x in [0..repo.user_name.length-1]
  		row2=$('<tr>')
  		row2.append $('<td style="border:1px solid black" />').append repo.user_name[x]
  		row2.append $('<td style="border:1px solid black"/>').append repo.repo_name[x]
  		row2.append $('<td style="border:1px solid black"/>').append repo.role_name[x]
  		row2.append $('<td style="border:1px solid black"/>').append repo.user_url[x]
  		row2.append $('<tr />')
    $('#repository_collab').append row2
    
 	

  	  	