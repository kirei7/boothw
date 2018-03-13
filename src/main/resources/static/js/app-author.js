$(document).ready(function(){
    $('form').on('submit', function (e) {
          e.preventDefault();
          var data = {
            "title": $("#title").val()
          };
          data = JSON.stringify(data);
          $.ajax({
            type: 'post',
            contentType: "application/json",
            url: $('form').attr('action'),
            data: data,
            success: function (data) {
              location.reload();
            }
          });
        });
});
