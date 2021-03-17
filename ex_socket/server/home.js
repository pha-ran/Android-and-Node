var app = require('express')();
var server = require('http').createServer(app);
var io = require('socket.io')(server);
var mongoose = require('mongoose');

// Port setting
var port = 2021;
server.listen(port, function(){
  console.log('server on! http://localhost:'+port);
});

// DB setting (@5.11.15)
mongoose.set('useNewUrlParser', true);
mongoose.set('useFindAndModify', false);
mongoose.set('useCreateIndex', true);
mongoose.set('useUnifiedTopology', true);
mongoose.connect(process.env.MONGO_DB);
var db = mongoose.connection;
db.once('open', function(){
  console.log('DB connected');
});
db.on('error', function(err){
  console.log('DB ERROR : ', err);
});

// DB schema
var contactSchema = mongoose.Schema({
  name:String
});
var Contact = mongoose.model('contact', contactSchema);

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
      // 받은 오브젝트로 mongoDB에 데이터를 생성
      Contact.create(obj, function(err, contact){
        if(err) console.log('error : ' + (err));
        console.log(contact);
      });
	  });

});

app.get('/', function(req,res) {
  console.log("get '/'");
  res.send('server is running on port '+port);
});
