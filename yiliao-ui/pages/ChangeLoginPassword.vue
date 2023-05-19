<template>
	<view class="page">
		<van-nav-bar
			title="修改登录密码"
			left-arrow
			:border="false"
			fixed
			safe-area-inset-top
			@click-left="$base.BackPage('/pages/AccountSafe')"
			>
		</van-nav-bar>
		<view class="wrap">
			<view class="from-input">
				<label>原登录密码</label>
				<input type="text" class="input-text" placeholder="请输入登录密码"/>
			</view>
			<view class="from-input">
				<label>新登录密码</label>
				<input type="text" class="input-text" placeholder="请输入新登录密码"/>
			</view>
			<view class="from-input">
				<label>确认密码</label>
				<input type="text" class="input-text" placeholder="请输入确认密码"/>
			</view>
			<view class="btn-class">实名认证</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
			}
		},
		methods: {
			
		}
	}
</script>

<style scoped lang="scss">
.wrap{
	padding-top: 150upx;
	.from-input{
		margin: 40upx;
		display: flex;
		height: 90upx;
		label{
			width: 200upx;
			margin-right: 20upx;
			font-weight: 600;
		}
		.input-text{
			flex: 1;
			text-align: right;
		}
	}
}
</style>
