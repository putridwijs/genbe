var dataPen=[];
var formBiodata = {
	addData: function(){
		if($('#form-biodata').parsley().validate()){
			var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);
			console.log(dataResult);
			dataPen.push(dataResult);
			if ($.fn.DataTable.isDataTable('#tableBiodata')) {
				$('#tableBiodata').DataTable().clear();
				$('#tableBiodata').DataTable().destroy();
			}
			$('#tableBiodata').DataTable({
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
                                render: function (data, type, row) {
                                    return "<button class='btn-primary' onclick=formBiodata.setEditData('" + data.idPerson + "')>Edit</button>"
                                }
                            }
                 ]
			});
			$('#modal-biodata').modal('hide');
			$('#form-biodata').parsley().reset();
		} else {
			
		}
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
                    if (xhr.status == 200 || xhr.status == 201) {
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
                    	$('.table-responsive').remove();
                        $('#modal-biodata').modal('hide')
                   }
                },
                error: function (err) {
                    console.log(err);
                }
            });
            }
     }
    }

};
