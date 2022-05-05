package com.example.myapplication

class Note{

    var id:String?=null
    var titel:String?=null
    var note:String? =null
    var timestamp:String?=null

constructor(){

}
constructor(id:String,title:String,note:String,timestamp:String){
    this.id=id
    this.titel=titel
    this.note=note
    this.timestamp=timestamp
}

}
