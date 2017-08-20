//3Dchart

function chartbar_3d(title,ytext,elements,x_axisvalue,max,steps){
     var data_2={     
            "title":{  
                "text": title,
                "style": "{font-size: 20px; color:#0000ff; font-family: Verdana; text-align: center;}"
            },  
            
            "y_legend":{
            "text": ytext,
            "style": "{color: #736AFF; font-size: 12px;}"
            },
           
            "elements":[  
                {  
                    "type":"bar_3d", 
                    "values": elements
                } 
            ],   
            "x_axis":{  
                "stroke":1,
			    "tick_height":10,
			    "colour":"#d000d0",
			    "grid_colour":"#00ff00",
                "labels":{  
                    "labels": x_axisvalue
                }  
            },  
            "y_axis":{  
                "stroke":      4,
			    "tick_length": 3,
			    "colour":      "#d000d0",
			    "grid_colour": "#00ff00",
			    "offset":      0,
			    "max":         max,         
			    "steps": steps
            }  
        };
     return data_2;     
        }
//线图
 function chartline_dot(title,ytext,elements,x_axisvalue,max,steps){
     var data_2={     
            "title":{  
                "text": title,
                "style": "{font-size: 20px; color:#0000ff; font-family: Verdana; text-align: center;}"
            },  
            
            "y_legend":{
            "text": ytext,
            "style": "{color: #736AFF; font-size: 12px;}"
            },
           
            "elements":[  
                {  
                    "type":"line",  
                    "values": elements
                }
                
              
            ],   
            "x_axis":{  
                "stroke":1,
			    "tick_height":10,
			    "colour":"#d000d0",
			    "grid_colour":"#00ff00",
                "labels":{  
                    "labels": x_axisvalue
                }  
            },  
            "y_axis":{  
                "stroke":      4,
			    "tick_length": 3,
			    "colour":      "#d000d0",
			    "grid_colour": "#00ff00",
			    "offset":      0,
			    "max":         max,         
			    "steps": steps
            }  
        };
     return data_2;     
        }
//横线图
 function charthbar(title,ytext,elements,x_axisvalue,max,steps){
     var data_2={     
            "title":{  
                "text": title,
                "style": "{font-size: 20px; color:#0000ff; font-family: Verdana; text-align: center;}"
            },  
            
            "y_legend":{
            "text": ytext,
            "style": "{color: #736AFF; font-size: 12px;}"
            },
           
            "elements":[  
                {  
                    "type":"hbar",  
                    "values": elements
                }  
            ],   
            "x_axis":{  
                "stroke":1,
			    "tick_height":10,
			    "colour":"#d000d0",
			    "grid_colour":"#00ff00",
                "labels":{  
                    "labels": x_axisvalue
                }  
            },  
            "y_axis":{  
                "stroke":      4,
			    "tick_length": 3,
			    "colour":      "#d000d0",
			    "grid_colour": "#00ff00",
			    "offset":      0,
			    "max":         max,         
			    "steps": steps
            }  
        };
     return data_2;     
        }
//饼状图
 function chartpie(title,ytext,elements,x_axisvalue,max,steps){
     var data_2={     
            "title":{  
                "text": title,
                "style": "{font-size: 20px; color:#0000ff; font-family: Verdana; text-align: center;}"
            },  
            
            "y_legend":{
            "text": ytext,
            "style": "{color: #736AFF; font-size: 12px;}"
            },
           
            "elements":[  
                {  
                    "type":"pie",  
                    "values": elements
                }  
            ],   
            "x_axis":{  
                "stroke":1,
			    "tick_height":10,
			    "colour":"#d000d0",
			    "grid_colour":"#00ff00",
                "labels":{  
                    "labels": x_axisvalue
                }  
            },  
            "y_axis":{  
                "stroke":      4,
			    "tick_length": 3,
			    "colour":      "#d000d0",
			    "grid_colour": "#00ff00",
			    "offset":      0,
			    "max":         max,         
			    "steps": steps
            }  
        };
     return data_2;     
        }
    
//柱形图      
     function chartbar(title,ytext,elements,x_axisvalue,max,steps){
     var data_2={     
            "title":{  
                "text": title,
                "style": "{font-size: 20px; color:#0000ff; font-family: Verdana; text-align: center;}"
            },  
            
            "y_legend":{
            "text": ytext,
            "style": "{color: #736AFF; font-size: 12px;}"
            },
           
            "elements":[  
                {  
                    "type":"bar_glass",  
                    "values": elements
                }  
            ],   
            "x_axis":{  
                "stroke":1,
			    "tick_height":10,
			    "colour":"#d000d0",
			    "grid_colour":"#00ff00",
                "labels":{  
                    "labels": x_axisvalue
                }  
            },  
            "y_axis":{  
                "stroke":      4,
			    "tick_length": 3,
			    "colour":      "#d000d0",
			    "grid_colour": "#00ff00",
			    "offset":      0,
			    "max":         max,         
			    "steps": steps
            }  
        };
     return data_2;     
        }