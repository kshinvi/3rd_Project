var net = new Object();

net.READY_STATE_UNINITIALIZED = 0;
net.READY_STATE_LOADING = 1;
net.READY_STATE_LOADED = 2;
net.READY_STATE_INTERACTIVE = 3;
net.READY_STATE_COMPLETE = 4;

net.ContentLoader = function(url, method, onload, onerror, bodys){
	this.url = url;
	this.req = null;
	this.body = bodys;
	this.onload = onload;
	this.method = (method) ? method : "GET";
	this.onerror = (onerror) ? onerror : this.defaultError;
	this.loadXMLDoc(url);
};

net.ContentLoader.prototype = {
	loadXMLDoc : function(url){
		if(window.XMLHttpRequest){
			this.req = new XMLHttpRequest();
		}else if(window.ActiveXObject){
			this.req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if(this.req){
			try {
				var loader = this;
				this.req.onreadystatechange = function(){
					loader.onReadyState.call(loader);
				};
				//alert(this.method+"\r\n"+this.body);
				//rest 처리추가
				this.req.open(this.method, url, true);
				//put일때 처리 추가
				if(this.method == "POST" || this.method == "PUT"){
					this.req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
					this.req.setRequestHeader("Content-length", this.body.length);
					this.req.setRequestHeader("Connection", "close");
				}
				this.req.send(this.body);
				//rest 처리 추가 끝
			} catch (e) {
				this.onerror(this);
			}
		}
	},
	onReadyState:function(){
		var req = this.req;
		var ready = req.readyState;
		if(ready == net.READY_STATE_COMPLETE) {
			var httpStatus = req.status;
			if(httpStatus == 200 || httpStatus == 0){
				this.onload.call(this);
			}else{
				this.onerror(this);
			}
		}
	},
	defaultError:function(){
		alert("error fetching data!"
				+"\n\nreadyState:" + this.req.readyState
				+"\nstatus: " + this.req.status
				+"\nheaders: \n" + this.req.getAllResponseHeaders());
	}
};