import React, {Component} from "react";
import Axios from "../../axios/axios";


class Marked extends Component{
    constructor(props) {
        super(props);
        this.state={
            markedList:[],

        }
    }

    componentDidMount (){
        Axios.get("/blog/personalTest", {
            params: { 'uid': uid }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    user:data.detail.userInfo,
                    articles:data.detail.articleInfo,
                });
            }else{
                alert(data.description)
            }
        }).catch( error => {
            alert(error.message)
        })
    }

    render() {
        return(
            <div className="blogRight">
                
            </div>
        )

    }
}

export default Marked