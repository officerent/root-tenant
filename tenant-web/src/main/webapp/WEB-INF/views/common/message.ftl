    <#if message??>
        <div class="panel-body">
            <div>
                <#if message == 1>
                    <div class="alert alert-success fade in">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>Well done!</strong> You successfully read this important alert message.
                    </div>
                </#if>
                <#if message == 2>
                    <div class="alert alert-info fade in">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>Heads up!</strong> This alert needs your attention, but it's not super important.
                    </div>
                </#if>
                <#if message == 3>
                    <div class="alert alert-warning fade in">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>Warning!</strong> Best check yo self, you're not looking too good.
                    </div>
                </#if>
                <#if message == 10>
                    <div class="alert alert-warning fade in">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>请勿添加!该问卷已有反馈</strong>
                    </div>
                </#if>
                <#if message == 11>
                    <div class="alert alert-warning fade in">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>请勿删除!该问卷已有反馈</strong>
                    </div>
                </#if>
            </div>
        </div><!-- /panel-body -->
    </#if>