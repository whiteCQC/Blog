export const dateTransfer=(strDate)=> {
    var date = new Date(strDate);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()+" "+date.getHours()+":"+
        date.getMinutes()+":"+date.getSeconds()
}

export const dateTransferSimple=(strDate)=> {
    var date = new Date(strDate);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
}