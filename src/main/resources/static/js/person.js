var formBiodata = {
	submitForm : function () {
		if ($('#dataperson').parsley().validate()){
			var dataResult = getJsonForm($('#dataperson').serializeArray(),true);
			
			$.ajax({
				url: '/person',
				method: 'post',
				contentType: 'application/json',
				dataType: 'json',
				data: JSON.stringify(dataResult),
				success: function (result){
				},
				error: function (err){
					console.log(err);
				}
			});
		}
	}
};
