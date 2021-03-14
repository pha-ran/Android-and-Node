var app = require('express')();
var server = require('http').createServer(app);
var io = require('socket.io')(server);

// Port setting
var port = 2021;
server.listen(port, function(){
  console.log('server on! http://localhost:'+port);
});

// 클라이언트에서 connect에 성공 시 호출
io.on('connection', function(socket) {
  console.log("new client connected");

  // 클라이언트의 연결이 끊어졌을 때 호출
  socket.on('disconnect', function(){
		console.log('server disconnected');
	});

  // 오류가 난 경우 호출
  socket.on('error', function(error){
    console.log('error : ' + error);
  });

  // 'message'라는 이름의 event를 받았을 경우 호출
  socket.on('message', function(obj){
		console.log('server received data : ' + obj);
    socket.emit('message', "OK");
	});

});

app.get('/', function(req,res) {
  console.log("get '/'");
  res.send('server is running on port '+port);
});
