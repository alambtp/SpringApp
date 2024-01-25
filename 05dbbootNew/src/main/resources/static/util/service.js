sap.ui.define(
	["jquery.sap.global"],
	function (jQuery) {
		return {
			callService: function (sUrl, sMethod, oPayload) {
				return new Promise(function (resolve, reject) {
					switch (sMethod.toUpperCase()) {
						case "GET":
							jQuery.ajax(sUrl, {
								type: 'GET',
								success: function (data) {
									console.log(data);
									resolve(data)
								},
								error: function (err) {
									console.log(data);
									reject(err);
								}
							});
							break;
						case "POST":
							jQuery.ajax(sUrl, {
								type: 'POST',
								contentType: 'application/json',
								data: JSON.stringify(oPayload),
								success: function (data) {
									console.log(data);
									resolve(data)
								},
								error: function (err) {
									console.log(data);
									reject(err);
								}
							});
							break;
						case "DELETE":
							jQuery.ajax(sUrl, {
								type: 'DELETE',
								contentType: 'application/json',
								data: JSON.stringify(oPayload),
								success: function (data) {
									console.log(data);
									resolve(data)
								},
								error: function (err) {
									console.log(data);
									reject(err);
								}
							});
							break;
					}
				});
			}
		}
	});