<div class="row modal-footer">
    <div class="col-sm-6">
        <div class="dataTables_info text-left">
        	<#if page.totalElements == 0>
        		0
			<#else>
				${page.number * page.size + 1 }
			</#if>
        to
		<#if (page.number + 1) * page.size < page.totalElements>
        	${(page.number + 1) * page.size}
		<#else>
        	${page.totalElements}
		</#if>
        	 共 ${page.totalElements} 
        </div>
    </div>
	<div class="col-sm-6">
		<div class="dataTables_paginate paging_bootstrap">
			<ul class="pagination">
				<#if page.totalPages gt 0 >
					<li class="prev<#if page.number==0> disabled</#if>">
						<a href='javascript:goto_page(${page.number-1});'> <i
							class="fa fa-angle-double-left"></i>
					</a>
					</li>
					<#if page.totalPages < 5 >
						<#list  0..page.totalPages - 1  as p>
							<#if p!=page.number>
								<li><a target="_self" href="javascript:goto_page(${p});">${p+1}</a></li>
							</#if>
							<#if p==page.number>
								<li class="active"><a>${p+1}</a></li>
							</#if>
						</#list>
					</#if>
					<#if page.totalPages gt 4 >
						<#if 0!=page.number>
							<li><a target="_self" href="javascript:goto_page(0);">1</a></li>
						</#if>
						<#if 0==page.number>
							<li class="active"><a>1</a></li>
						</#if>

						<#if page.number<3>
							<#list 1..3 as p>
								<#if p!=page.number>
									<li><a target="_self" href="javascript:goto_page(${p});">${p+1}</a></li>
								</#if>
								<#if p==page.number>
									<li class="active"><a>${p+1}</a></li>
								</#if>
							</#list>
							<li><a>...</a></li>
						</#if>
						<#if page.number gt 2 && page.number<page.totalPages-2>
							<li><a>...</a></li>
							<#list page.number - 1..page.number + 1 as p>
								<#if p!=page.number>
									<li><a target="_self" href="javascript:goto_page(${p});">${p+1}</a></li>
								</#if>
								<#if p==page.number>
									<li class="active"><a>${p+1}</a></li>
								</#if>
							</#list>
							<li><a>...</a></li>
						</#if>
						<#if page.number gt page.totalPages-3>
							<li><a>...</a></li>
							<#list page.totalPages-4..page.totalPages-2 as p>
								<#if p!=page.number>
									<li><a target="_self" href="javascript:goto_page(${p});">${p+1}</a></li>
								</#if>
								<#if p==page.number>
									<li class="active"><a>${p+1}</a></li>
								</#if>
							</#list>
						</#if>
						<#if page.totalPages!=page.number+1>
							<li><a target="_self"
								href="javascript:goto_page(${page.totalPages-1});">${page.totalPages}</a></li>
						</#if>
						<#if page.totalPages==page.number+1>
							<li class="active"><a>${page.totalPages}</a></li>
						</#if>

					</#if>
					<li
						class='next<#if page.totalPages==page.number + 1> disabled</#if>'>
						<a target="_self" href='javascript:goto_page(${page.number+1});'>
							<i class="fa fa-angle-double-right"></i>
					</a>
					</li>
				</#if>
			</ul>
		</div>
	</div>
</div>


<input type="hidden" id="pageNo" name="number" value="${page.number}" />
<input type="hidden" id="pageSize" name="size" value="20" />
<input type="hidden" id="total" value="${page.totalPages }" />
<script type="text/javascript">
	function goto_page(page, form_id) {
		var total = document.getElementById('total').getAttribute("value");
		if (total > page && page >= 0) {// 首页和末页不执行
			if (!form_id)
				form_id = 'form1';
			document.getElementById('pageNo').value = page;
			document.getElementById(form_id).submit();
		}
	}
</script>