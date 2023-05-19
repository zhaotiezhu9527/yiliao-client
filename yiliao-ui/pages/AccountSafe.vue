<template>
	<view class="page">
		<van-nav-bar
			title="账户安全"
			left-arrow
			:border="false"
			fixed
			safe-area-inset-top
			@click-left="$base.BackPage('/pages/personal')"
			>
		</van-nav-bar>
		<view class="wrap">
			<view class="from-input">
				<input type="text" class="input-text" placeholder="请输入所属银行，如：中国工商银行"/>
			</view>
			<view class="from-input">
				<input type="text" class="input-text" placeholder="请输入支行信息，如：北京朝阳支行"/>
			</view>
			<view class="from-input">
				<input type="text" class="input-text" placeholder="请输入储蓄卡号"/>
			</view>
			<view class="btn-class">提交绑定</view>
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
	padding-top: 104upx;
	.usdt-title{
		margin: 60upx 50upx;
		color: #577fab;
		font-weight: 600;
		font-size: 40upx;
	}
	.from-input{
		margin: 40upx;
		height: 90upx;
		.input-text{
			height: 64upx;
			font-size: 28upx;
		}
	}
}
</style>
