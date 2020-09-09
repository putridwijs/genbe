var tableBiodata = {
	create: function () {
		if ($.fn.DataTable.isDataTable('#tableBiodata')) {
			$('#tableBiodata').DataTable().clear();
			$('#tableBiodata').DataTable().destroy();
		}
		
		$.ajax({
			url: '/api/pendidikan',
			method: 'get',
			contentType: 'application/json',
			success: function (res, status, xhr){
				if (xhr.status == 200 || xhr.status == 201){
					console.log(res);
					$('#tableBiodata').DataTable({
						data: res,
						columns: [
							{
								title: "Id Person",
								data: 'idPerson'
							},
							{
								title: "Jenjang",
								data: 'jenjang'
							},
							{
								title: "Institusi",
								data: 'institusi'
							},
							{
								title: "Tahun Masuk",
								data: 'tahunMasuk'
							},
							{
								title: "Tahun Lulus",
								data: 'tahunLulus'
							},
							{
                                title: "Edit Data",
                                data: null,
                                render: function (data, type, row) {
                                    return "<button class='btn-primary' onclick=formBiodata.setEditData('" + data.idPerson + "')>Edit</button>"
                                }
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
    addField: function () {
    	var wrapper = $('.fieldset');
    	$(wrapper).append('<div class="form-group">'
							+'<label>Jenjang</label>'
							+'<select class="form-control custom-select" id="jenjang" name="jenjang">'
							+	'<option value="">--PILIH JENJANG PENDIDIKAN--</option>'
							+	'<option value="SD">SD</option>'
							+	'<option value="SMP">SMP</option>'
							+	'<option value="SMA/SMK">SMA</option>'
							+	'<option value="S1">S1</option>'
							+	'<option value="S2">S2</option>'
							+	'<option value="S3">S3</option>'
							+'</select></div>'
						
						+'<div class="form-group">'
						+	'<label>Institusi</label>'
						+	'<input class="form-control" id="institusi" name="institusi" placeholder="jenjang" required type="text"></div>'
						
						+'<div class="form-group"><label>Tahun Masuk</label>'
						+	'<input class="form-control" id="tahunMasuk" name="tahunMasuk" placeholder="tahun masuk" required type="text"></div>'
						
						+'<div class="form-group"><label>Tahun Lulus</label>'
						+	'<input class="form-control" id="tahunLulus" name="tahunLulus" placeholder="tahun lulus" required type="text"></div>'
    );
    },
    saveForm: function () {
    if ($('#form-biodata').parsley().validate()) {
            var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);
			var arr= [];
			arr.push(dataResult);
            $.ajax({
                url: '/pendidikan?idPerson=' + $('#idPerson').val(),
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(arr),
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
    }

};
