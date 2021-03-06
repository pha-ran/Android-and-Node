var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');
var app = express();

mongoose.set('useNewUrlParser', true);
mongoose.set('useFindAndModify', false);
mongoose.set('useCreateIndex', true);
mongoose.set('useUnifiedTopology', true);
mongoose.connect(process.env.MONGO_DB);
var db = mongoose.connection;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
app.use(express.static(__dirname+'/public'));

var retrofitSchema = mongoose.Schema({
  title:{type:String, required:true, unique:true},
  body:{type:String}
});
var RetrofitDB = mongoose.model('retrofit', retrofitSchema);

db.once('open', function(){
  console.log('DB connected');
});

db.on('error', function(err){
  console.log('DB ERROR : ', err);
});

var port = 2021;
app.listen(port, function(){
  console.log('server on! http://localhost:'+port);
});

//--------------------------------------------------

app.get('/', function(req, res) {
  console.log("get '/'");
});

app.get('/data', function(req, res) {
  console.log("get '/data'");
  RetrofitDB.find({}, function(err, datas){
    if(err) return console.log(err);
    res.json(datas);
  });
});

app.post('/data', function(req, res) {
  console.log("post '/data'");
  console.log(req.body);
  RetrofitDB.create(req.body, function(err, data){
    if(err) return console.log(err);
    res.json(data);
  });
});

app.put('/data/:title', function(req, res) {
  RetrofitDB.findOneAndUpdate({title:req.params.title}, req.body, function(err, data) {
    if(err) return console.log(err);
    console.log("put");
  });
});

app.delete('/data/:title', function(req, res) {
  RetrofitDB.deleteOne({title:req.params.title}, function(err){
    if(err) return console.log(err);
    console.log("delete");
  })
})
