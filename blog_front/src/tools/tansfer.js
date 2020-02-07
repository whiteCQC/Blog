export const dateTransfer=(strDate)=> {
    var date = new Date(strDate);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()+" "+date.getHours()+":"+
        date.getMinutes()+":"+date.getSeconds()
}

export const dateTransferSimple=(strDate)=> {
    var date = new Date(strDate);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
}

export const cutstr = (str, len)=> {
    var str_length = 0;
    var str_len = 0;
    let str_cut;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        let a;
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；
    if (str_length < len) {
        return str;
    }
}