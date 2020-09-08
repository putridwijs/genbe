var tableBiodata = {
	create: function () {
		if ($.fn.DataTable.isDataTable('#tableBiodata')) {
			$('#tableBiodata').DataTable().clear();
			$('#tableBiodata').DataTabel().destroy();
		}
		
		$.ajax({
			url: '/api/person',
			method: 'get',
			contentType: 'application/json',
			success: function (res, status, xhr){
				if (xhr.status == 200 || xhr.status == 201){
					console.log(res);
					$('#tableBiodata').DataTable({
						data: res,
						column: [
							{
								title: "NIK",
								data: 'nik'
							}
                       ]
                  });
              } else {
              
              }
	},
	error: function (err) {
		console.log(err);
	}
});
}
};

var formBiodata = {
	resetForm: function () {
        $('#form-biodata')[0].reset();
    },
    saveForm: function () {
    if ($('#form-biodata').parsley().validate()) {
            var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);

            $.ajax({
                url: '/api/person',
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(dataResult),
                success: function (res, status, xhr) {
                    if (xhr.status == 200 || xhr.status == 201) {
                        $('#modal-biodata').modal('hide')

                    } else {

                    }
                },
                erorrr: function (err) {
                    console.log(err);
                }
            });
     }
    },
    setEditData: function (idCabang) {
        formBiodata.resetForm();

        $.ajax({
            url: '/api/biodata/' + idCabang,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#form-biodata').fromJSON(JSON.stringify(res));
                    $('#modal-biodata').modal('show')

                } else {

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        });


    }

};
