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
						columns: [
							{
								title: "NIK",
								data: 'nik'
							},
							{
								title: "Nama",
								data: 'nama'
							},
							{
								title: "Alamat",
								data: 'alamat'
							},
							{
								title: "No Hp",
								data: 'noHp'
							},
							{
								title: "Tanggal Lahir",
								data: 'tanggalLahir'
							},
							{
								title: "Tempat Lahir",
								data: 'tempatLahir'
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
	resetform: function () {
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
                error: function (err) {
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
            error: function (err) {
                console.log(err);
            }
        });


    }

};
