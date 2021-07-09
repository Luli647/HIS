
export function dateFormat (date){
    var dateObj = new Date(date);
    console.log(dateObj);
    var year = dateObj.getFullYear();
  var month = ("0" + (dateObj.getMonth() + 1)).slice(-2);
  var day = ("0" + dateObj.getDate()).slice(-2);
  return year + "-" + month + "-" + day;
};