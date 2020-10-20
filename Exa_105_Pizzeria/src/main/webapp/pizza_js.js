function validate(){
    let numbers = document.getElementsByClassName("number_input");
    
    let counter = 0;
    
    for(let i = 0; i<numbers.length; i++){
        n = numbers[i];
        if(n.value != "" && n.value != "0"){
            counter++;
        }
    }
    
    if(counter==0){
        alert("Please order at least one Pizza!");
        return false;
    }
    
    if(document.getElementById("address_input").value == ""){
        alert("Please enter an Address!");
        return false;
    }
    return true;
}