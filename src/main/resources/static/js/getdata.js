var tableBiodata = {
	create: function () {
		if ($.fn.DataTable.isDataTable('#tableBiodata')) {
			$('#tableBiodata').DataTable().clear();
			$('#tableBiodata').DataTable().destroy();
		}
		
		$.ajax({
			url: '/api/person/semuadata',
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
