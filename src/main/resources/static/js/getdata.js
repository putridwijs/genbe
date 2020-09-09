var tableBiodata = {
	create: function () {
		if ($.fn.DataTable.isDataTable('#tableBiodata')) {
			$('#tableBiodata').DataTable().clear();
			$('#tableBiodata').DataTable().destroy();
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
							},
							{
                                title: "Action",
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
    getData: function (nik) {
        $.ajax({
            url: '/api/person/' + nik,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (result) {
                if (result[0].status == "true") {
                    $('#tableBiodata').DataTable({
						data: [result[0].data],
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
							},
							
							{	
								title: "Umur",
								data: 'umur'
							},
							{
								title: "Pendidikan Terakhir",
								data: 'pendidikanTerakhir'
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