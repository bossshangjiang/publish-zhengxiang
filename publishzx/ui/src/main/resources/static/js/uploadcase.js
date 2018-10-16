function submitcase(){
    console.log("submit");
    var age = $('#age').val();
    var libexam = $('#libexam').val();
    var specialexam =$('#specialexam').val();
    var diagnose = $('#diagnose').val();
    var difference = $('#difference').val();
    var sex1 = $('#sex1').val();
    var sex2 = $('#sex2').val();

    var obj = {
        age: age,
        libexam: libexam,
        specialexam:specialexam,
        diagnose:diagnose,
        difference:difference,
        sex1:sex1,
        sex2:sex2
    };

    var data = JSON.stringify(obj);
    console.log(data);


    $.ajax({
        type:"POST",
        url:"/uploadcase",
        data:data,
        error: function() {
            console.log();
            alert("提交失败");
        },
        success:function(data){
            console.log("返回数据为");
            console.log(data);
            alert("提交成功");
        }
    });
}
function savecase(){

}

$(document).ready(function () {
    console.log('a');
   $("#btnCT").on('click', function () {
       console.log('addctimg');
       var html = $('<label for="inputct1">请选择CT影像文件</label>\n' +
           '         <input type="file" id="inputct1" name="inputctimgs" />\n' +
           '         <input type="text" class="form-control" id="inputctdescription1" name="inputctdescription1"/>');
       $("#ctimg").append(html);
   });


    $("#btnMRI").on('click', function () {
        console.log('addctimg');
        var html = $('<label for="inputmri1">请选择影像文件</label>\n' +
            '            <input type="file" id="inputmri1" name="inputmriimgs" />\n' +
            '            <input type="text" id="inputmridescription1" name="inputmridescription1"/>');
        $("#mriimg").append(html);
    });

});
