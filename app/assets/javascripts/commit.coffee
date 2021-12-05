$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onopen = (event) ->
    event.preventDefault()
    url = $(location).attr('href')
    token = url.split('/')
    repository = token[5]
    user = token[4]
    $('#title').text("Commit Statistics for "+repository+" Repository")
    ws.send(JSON.stringify({user: user, repository: repository}))
    
  ws.onmessage = (event) ->
    commitStat = JSON.parse event.data
    list = commitStat.author_list.split(',')
    $('#topCommitters').empty()
    for item in list
    	arr = item.split('@')
    	$('#topCommitters').append $('<li/>').append $('<div><a href="/users/'+arr[1]+'">'+arr[0]+'</a>' +' :'+arr[2]+' Commits</div>')
    $('#min_add').text(commitStat.min_add)
    $('#max_add').text(commitStat.max_add)
    $('#avg_add').text(commitStat.avg_add)
    $('#min_del').text(commitStat.min_del)
    $('#max_del').text(commitStat.max_del)
    $('#avg_del').text(commitStat.avg_del)