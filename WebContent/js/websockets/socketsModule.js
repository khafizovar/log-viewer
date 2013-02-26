//��������
var SocketModule = {

	// ������� ������ ������
	socket : null,
	//������������� �����
	gridName: null,
	//��� ������
	name: 'socketModule',
	//������� ������������� ������� ������ �����
	refreshGrid: true,
	// ����� �������������
	init : function(socketUrl, gridId) { // ws://10.15.6.140:8181/
		//������������� �����
		this.gridName = gridId;
		//������ ��� ������
		this.socket = new WebSocket(socketUrl);
		//��������� �������� ������
		this.socket.onopen = function() {
			console.log("���������� ���������");
		};
		//��������� �������� ������
		this.socket.onclose = function() {
			console.log("���������� ���������");
		};
		//��������� ��������� ��������� ������
		this.socket.onmessage = function(event) {
			console.log("������ ��������� � �����������:", event.data);
			if(SocketModule.refreshGrid === true) {
				var grid = $("#" + SocketModule.gridName).data("kendoGrid");
	        	var new_data = jQuery.parseJSON(event.data);
	        	grid.dataSource.data(new_data);
			}
		};

	},
	//�������� ��������� � �����
	sendMessage : function(data) {
		this.socket.send("test from web");
	}
	
	

};