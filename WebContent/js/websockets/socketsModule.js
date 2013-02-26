//Синглтон
var SocketModule = {

	// Будущий объект сокета
	socket : null,
	//Идентификатор грида
	gridName: null,
	//Имя модуля
	name: 'socketModule',
	//Признак необходимости рефреша данных грида
	refreshGrid: true,
	// Метод инициализации
	init : function(socketUrl, gridId) { // ws://10.15.6.140:8181/
		//Идентификатор грида
		this.gridName = gridId;
		//Объект веб сокета
		this.socket = new WebSocket(socketUrl);
		//Обработка открытия сокета
		this.socket.onopen = function() {
			console.log("Соединение открылось");
		};
		//Обработка открытия сокета
		this.socket.onclose = function() {
			console.log("Соединение открылось");
		};
		//Обработка получения сообщения сокета
		this.socket.onmessage = function(event) {
			console.log("Пришло сообщение с содержанием:", event.data);
			if(SocketModule.refreshGrid === true) {
				var grid = $("#" + SocketModule.gridName).data("kendoGrid");
	        	var new_data = jQuery.parseJSON(event.data);
	        	grid.dataSource.data(new_data);
			}
		};

	},
	//Отправка сообщения в сокет
	sendMessage : function(data) {
		this.socket.send("test from web");
	}
	
	

};