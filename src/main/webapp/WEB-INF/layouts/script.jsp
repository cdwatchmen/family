	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%
		String easyuiThemeName="metro";
		Cookie cookies[] =request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals("cookiesColor")) {
					easyuiThemeName = cookie.getValue();
					break;
				}
			}
		}
	%>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/themes/<%=easyuiThemeName %>/easyui.css">
	<script type="text/javascript" src="${ctx}/assets/jstest/xheditor/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jstest/xheditor/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/custom/css/common.css">
	<script type="text/javascript" src="${ctx}/assets/jstest/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jstest/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jstest/jqueryUtil.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jstest/json2.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jstest/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jstest/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jstest/FusionCharts/FusionChartsExportComponent.js"></script>
	<script type="text/javascript">
		$(function(){
				
               /* $('#tt').tabs({
                    onLoad:function(panel){
                        var plugin = panel.panel('options').title;
                    },
                    tools:[{
                            iconCls:'icon-color',
                            handler:function(){
                                //window.open("http://easyui.btboys.com/reply.html");
                              
                               $('#mm').menu({   
								    onClick:function(item){   
                            	   			var cookiesColor1=jqueryUtil.cookies.get("cookiesColor");
                            	   		if(cookiesColor1!=item.id){
								        	jqueryUtil.cookies.set("cookiesColor",item.id,30);
								        	jqueryUtil.chgSkin(item.id,cookiesColor1);
                            	   		}
								    }   
								});
                                $('#mm').menu('show', {   
								  left: 1222,   
								  top: 90   
								});
                            }
                        }],
						extractor:function(data){
							var tmp = $('<div></div>').html(data);
							data = tmp.find('#content').html();
							tmp.remove();
							return data;
						}
                });
				try{
				　　top.location.hostname;
				　　if (top.location.hostname != window.location.hostname) {
				　　　　top.location.href =window.location.href;
				　　}
				}
				catch(e){
				　　top.location.href = window.location.href;
				}*/
				
            });
	</script>
	<style type="text/css">
		body {
		    font-family:helvetica,tahoma,verdana,sans-serif;
		    font-size:13px;
		    margin:0px 0px 0px 0px;
		    padding:0px 0px 0px 0px;
		}
	</style>