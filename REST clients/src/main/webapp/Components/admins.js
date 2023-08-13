$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

// SAVE
$(document).on("click","#btnSave", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateAdminForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // if hidAdminIDSave value is null set as POST else set as PUT
    var type = ($("#hidAdminIDSave").val() == "") ? "POST" : "PUT";

    // ajax communication
    $.ajax({
        url: "AdminAPI",
        type: type,
        data: $("#formAdmin").serialize(),
        dataType: "text",
        complete: function(response, status) {
            onAdminSaveComplete(response.responseText, status);
        }
    });
});

// after completing save request
function onAdminSaveComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully saved");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divAdminsGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while saving");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while saving");
        $("#alertError").show();
    } 

    //resetting the form
    $("#hidAdminIDSave").val("");
    $("#formAdmin")[0].reset();
}

// UPDATE
$(document).on("click", ".btnUpdate", function(event) 
{ 
    //get user id from the data-userid attribute in update button
    $("#hidAdminIDSave").val($(this).data('userid')); 
    //get data from <td> element
    $("#firstName").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#lastName").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
    $("#mobile").val($(this).closest("tr").find('td:eq(4)').text()); 
    $("#serviceNo").val($(this).closest("tr").find('td:eq(5)').text()); 
    $("#department").val($(this).closest("tr").find('td:eq(6)').text()); 
    $("#position").val($(this).closest("tr").find('td:eq(7)').text());
}); 

// DELETE
$(document).on("click",".btnRemove", function(event) {
    // ajax communication
    $.ajax({
        url: "AdminAPI",
        type: "DELETE",
        data: "userID=" + $(this).data("userid"),
        dataType: "text",
        complete: function(response, status) {
            onAdminDeleteComplete(response.responseText, status);
        }
    });
});

// after completing delete request
function onAdminDeleteComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully deleted");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divAdminsGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while deleting");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while deleting");
        $("#alertError").show();
    } 
}

// VALIDATION
function validateAdminForm() { 
    // FIRSTNAME
    if ($("#firstName").val().trim() == "") 
    { 
        return "Insert First Name."; 
    } 
    
    // LASTNAME 
    if ($("#lastName").val().trim() == "") 
    { 
        return "Insert Last Name."; 
    } 
    
    // EMAIL
    if ($("#email").val().trim() == "") 
    { 
        return "Insert Email."; 
    } 
    
    // MOBILE
    if ($("#mobile").val().trim() == "") 
    { 
        return "Insert Mobile."; 
    }
    
    // SERVICENO
    if ($("#serviceNo").val().trim() == "") 
    { 
        return "Insert Service Number."; 
    }
    
    // DEPARTMENT
    if ($("#department").val().trim() == "") 
    { 
        return "Insert Department."; 
    }
    
    // POSITION
    if ($("#position").val().trim() == "") 
    { 
        return "Insert Position."; 
    }
    
    return true; 
} 
 