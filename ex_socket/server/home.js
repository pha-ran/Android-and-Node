var app = require('express')();
var server = require('http').createServer(app);
var io = require('socket.io').listen(server);

// Port setting
var port = 2021;
server.listen(port, function(){
  console.log('server on! http://localhost:'+port);
});

// 클라이언트에서 connect에 성공 시 호출
io.on('connection', function(socket) {
  console.log("new client connected");
});

app.get('/', function(req,res) {
  console.log("get '/'");
  res.send('server is running on port '+port);
});
