$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onmessage = (event) ->
    repo = JSON.parse event.data
    $('#name').text(repo.name)
    $('#id').text(repo.id)
    $('#node_id').text(repo.node_id)
    $('#avatar_url').text(repo.avatar_url)
    $('#repos_url').text('<a href="/users/'+repo.repos_url+'">'+repo.repos_url+'</a>')
    $('#email').text(repo.email)
    $('#twitter_username').text(repo.twitter_username)
    $('#followers').text(repo.followers)
    $('#following').text(repo.following)
    $('#subscriptions_url').text(repo.subscriptions_url)
    $('#organizations_url').text(repo.organizations_url)