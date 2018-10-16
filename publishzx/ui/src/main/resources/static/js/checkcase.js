function changestatus1() {
    var data = 'caseid='+$('#caseid').text()+'&note='+$('#note').val();

    var checkcase = 'note='+$('#note').val()+'&caseid='+$('#thecheckcasea._id').text();
    $.ajax({
        type: "POST",
        url: "/changestatus1",
        data:data,
        success: function(r)
        {
            alert(r);
            if(r=="success"){
                alert("审核通过成功");
                window.location.href = '/tobedone';
                return false;
            }
        }
    });

}

function changestatus2() {
    var data = 'caseid='+$('#caseid').text()+'&note='+$('#note').val();

    var checkcase = 'note='+$('#note').val()+'&caseid='+$('#thecheckcasea._id').text();
    $.ajax({
        type: "POST",
        url: "/changestatus2",
        data:data,
        success: function(r)
        {
            if(r=="success"){
                alert("审核不通过成功");
                window.location.href = '/tobedone';
                return false;
            }
        }
    });

}

function changestatus3() {
    var data = 'caseid='+$('#caseid').text()+'&note='+$('#note').val();

    var checkcase = 'note='+$('#note').val()+'&caseid='+$('#thecheckcasea._id').text();
    $.ajax({
        type: "POST",
        url: "/changestatus3",
        data:data,
        success: function(r)
        {
            if(r=="success"){
                alert("退修成功");
                window.location.href = '/tobedone';
                return false;
            }
        }
    });

}

function saveandmodify() {
    var data = 'caseid='+$('#caseid').text()+'&lab_exam='+$('#lab_exam').val()+'&extra_exam='+$('#extra_exam').val()+'&present_history='+$('#present_history').val()+'&according='+$('#according').val()+'&differential='+$('#differential').val();

    var checkcase = 'note='+$('#note').val()+'&caseid='+$('#thecheckcasea._id').text();
    $.ajax({
        type: "POST",
        url: "/saveandmodify",
        data:data,
        success: function(r)
        {
            if(r=="success"){
                alert("退修成功");
                window.location.href = '/tobedone';
                return false;
            }
        }
    });

}