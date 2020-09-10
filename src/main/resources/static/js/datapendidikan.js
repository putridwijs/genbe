var dataPen=[];
var newrow=-1;
var tableBiodata = {
	create: function () {
		if($('#form-biodata').parsley().validate()){
			if ($.fn.DataTable.isDataTable('#tableBiodata')) {
				$('#tableBiodata').DataTable().clear();
				$('#tableBiodata').DataTable().destroy();
			}
			$('#tableBiodata').DataTable({
				"info": false,
				"searching": false,
				"lengthChange": false,
				"paginate":false,
				data: dataPen,
				columns: [
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
                                render: function (data, type, row, meta) {
                                    return "<button class='btn-primary' onclick=formBiodata.setEditData('" + meta.row + "')>Edit</button>"
                                }
                            },
                            {
                                title: "Hapus",
                                data: null,
                                render: function (data, type, row, meta) {
                                    return "<button class='btn-danger' onclick=formBiodata.eraseRow('" + meta.row + "')>Hapus</button>"
                                }
                            }
                 ]
			});
			$('#modal-biodata').modal('hide');
			$('#form-biodata').parsley().reset();
		} else {
			
		}
	}
}
var formBiodata = {
	addData: function (){
		var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);
		console.log(dataResult);
		dataPen.push(dataResult);
		tableBiodata.create();
	},
	addDataBaru: function(){
		var newResult = getJsonForm($("#form-biodata").serializeArray(), true);
		dataPen[newrow] = newResult;
		tableBiodata.create();
		newrow=-1;
	},
	saveData: function (){
		tableBiodata.create();
	},
	resetform: function () {
        $('#form-biodata')[0].reset();
    },
    saveForm: function () {
    console.log(dataPen);
    if ($('#form-biodata').parsley().validate()) {
    	if ($('#idPerson').val() == null){
                    	Swal.fire({
					   		icon: 'error',
					   		title: 'Oops...',
					   		text: 'Harap Masukan ID Person'
						})
    	} else {
            $.ajax({
                url: '/api/pendidikan?idPerson=' + $('#idPerson').val(),
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(dataPen),
                success: function (res, status, xhr) {
                    error: function (err) {
                    console.log(err);
                    Swal.fire({
					   		icon: 'error',
					   		title: 'Oops...',
					   		text: 'ID kosong atau ID tidak ditemukan'
					})
                },
                if (xhr.status == 200 || xhr.status == 201) {
							$('#modal-biodata').modal('hide')
							$('#tableBiodata').remove();
							const Toast = Swal.mixin({
								toast: true,
								position: 'top-end',
								showConfirmButton: false,
								timer: 3000,
								timerProgressBar: true,
								onOpen: (toast) => {
									toast.addEventListener('mouseenter', Swal.stopTimer)
									toast.addEventListener('mouseleave', Swal.resumeTimer)
								}
							})
							Toast.fire({
								icon: 'success',
								title: 'Data berhasil masuk'
							})
							Swal.fire({
								icon: 'error',
								title: 'Oops...',
								text: 'ID tidak ditemukan'
							})
                }
            })
            }
		}
   	},
		setEditData: function (row) {
     	$('#form-biodata').fromJSON(JSON.stringify(dataPen[row]));
     	$('#modal-biodata').modal('show');
     	newrow= row;
    },
     eraseRow: function (row) {
     	$('#tableBiodata').dataTable(row).remove();
    }
};
