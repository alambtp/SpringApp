sap.ui.define(
	["sap/ui/core/mvc/Controller",
		"jquery.sap.global",
		"sap/m/MessageBox",
		"alam/util/service"],
	function (Controller, jQuery, MessageBox, service ) {
		return Controller.extend("alam.Controller.Main", {
			onInit: function () {
				var oModel = new sap.ui.model.json.JSONModel();
				oModel.setData({
					"postPayload": {
						"companyName": "",
						"firstName": "",
						"lastName": "",
						"email": "",
						"website": "",
						"gstNo": "",
						"status": "A"
					},
					"vendor": {}
				});
				sap.ui.getCore().setModel(oModel);
			},
			onSave: function () {
				var oModel = sap.ui.getCore().getModel();
				var payload = oModel.getProperty("/postPayload");
				service.callService("/vendor", "POST", payload).then(function () {
					MessageBox.success("Save Success");
				}).catch(function () {
					MessageBox.error("Post failed");
				});

			},
			onDelete: function(oEvnt){
				debugger;
				var that = this;
				var oTable = this.getView().byId("idTable");
				var sPath = oTable.getContextByIndex(oTable.getSelectedIndex()).sPath;
				var sUrl = "/vendor/" + this.getView().getModel().getProperty(sPath).id
				service.callService(sUrl, "DELETE", {}).then(function () {
					that.onLoadData();
					MessageBox.success("Deleted Successfully");
				}).catch(function () {
					MessageBox.error("Delete failed");
				});

			},
			onLoadData: function () {
				// alert("todo: We will call our microservice to load venders");
				var that = this;
				service.callService("/vendor", "GET", {}).then(function (data) {
					console.log(data);
					var oTable = that.getView().byId("idTable");
					var oModel = sap.ui.getCore().getModel();
					oModel.setProperty("/vendor", data);

					oTable.bindRows("/vendor");
				}).catch(function (oError) {
					console.log(oError);
				});
			}
		});
	});