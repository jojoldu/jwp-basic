// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);
$(".deleteBtn").click(deleteAnswer);

function deleteAnswer(e){
  e.preventDefault();
  var $this = $(e.target);
  var answerId = $this.closest('form').find('.answerId').val();

  $.ajax({
    type : 'post',
    url : '/api/qna/deleteAnswer',
    data : {"answerId": answerId},
    dataType : 'json',
    error: onError,
    success : function(){
      $this.closest('.article').hide();
    }
  });
}

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess
  });
}

function onSuccess(json, status){
  var answer = json.answer;
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
}

function onError(xhr, status) {
  alert("error");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};