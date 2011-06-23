<script type="text/javascript">

    if (getCookie("username") != null) 
    {
        $("j_username").value = getCookie("username");
        $("j_password").focus();
    } 
    else 
    {
        $("j_username").focus();
    }
    
    function saveUsername(theForm) 
    {
        var expires = new Date();
        expires.setTime(expires.getTime() + 24 * 30 * 60 * 60 * 1000); // sets it for approx 30 days.
        setCookie("username",theForm.j_username.value,expires,"<c:url value="/"/>");
    }
    
    function validateForm(form) 
    {                                                               
        return validateRequired(form); 
    } 
    
    function passwordHint() 
    {
        if ($("j_username").value.length == 0) {
            alert("<fmt:message key="errors.required"><fmt:param><fmt:message key="label.username"/></fmt:param></fmt:message>");
            $("j_username").focus();
        } else {
            location.href="<c:url value="/passwordHintController.do"/>?username=" + $("j_username").value;     
        }
    }
    
    function required () 
    { 
        this.aa = new Array("j_username", "<fmt:message key="errors.required"><fmt:param><fmt:message key="label.username"/></fmt:param></fmt:message>", new Function ("varName", " return this[varName];"));
        this.ab = new Array("j_password", "<fmt:message key="errors.required"><fmt:param><fmt:message key="label.password"/></fmt:param></fmt:message>", new Function ("varName", " return this[varName];"));
    } 
    
    window.onload = function() 
    {
        highlightFormElements();    
    }

    function toggle(id)
    {
    	node = document.getElementById(id + '_Node');
    	
    	if(node.style.display == 'none')
    	{
    		node.style.display = 'block';
    	}
    	else
    	{
    		node.style.display = 'none';
    	}
    }

    function highlightFormElements() 
    {
        addFocusHandlers(document.getElementsByTagName("input"));
        addFocusHandlers(document.getElementsByTagName("textarea"));
    }

    function addFocusHandlers(elements) 
    {
        for (i=0; i < elements.length; i++) 
        {
            if (elements[i].type != "button" && elements[i].type != "submit" &&
                elements[i].type != "reset" && elements[i].type != "checkbox" && elements[i].type != "radio") 
            {
                if (!elements[i].getAttribute('readonly') && !elements[i].getAttribute('disabled')) 
                {
                    elements[i].onfocus=function() {this.style.backgroundColor='#ffd';this.select()};
                    elements[i].onmouseover=function() {this.style.backgroundColor='#ffd'};
                    elements[i].onblur=function() {this.style.backgroundColor='';}
                    elements[i].onmouseout=function() {this.style.backgroundColor='';}
                }
            }
        }
    }

    function getDate()
    {
    	var today=new Date();
    	var month = today.getMonth() + 1
    	var day = today.getDate()
    	var year = today.getFullYear()
    	
    	return month + "/" + day + "/" + year
    }

    function confirmDelete(obj) 
    {   
        var msg = "Are you sure you want to delete this " + obj + "?";
        
        ans = confirm(msg);
        
        if (ans) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }

    function getListBoxValues(elementId) 
    {
        var element = document.getElementById(elementId);
        
        len = element.length;
        
        if (len != 0) 
        {
            for (i = 0; i < len; i++) 
            {
                element.options[i].selected = true;
            }
        }
    }

    function gohome()
    {
    	if (document.layers)
    		window.home()
    	else if (document.all)
    		window.location="home.html"
    	else
    		alert("You need NS 4+ or IE 4+ to go back home!")
    }

    function goback()
    {
    	history.back()
    }
</script>