function re_show(val,num,btnName,divName,btncss1,btncss2){
		for(var i=1;i<=num;i++)
		{
			if(val==i)
			{
				document.getElementById(divName+i).style.display = 'block';
				document.getElementById(btnName+i).className = btncss1;
			}
			else{
				document.getElementById(divName+i).style.display = 'none';
				document.getElementById(btnName+i).className = btncss2;
			}
		}
	}