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
			<view class="content">
				<view class="list">
					<image class="icon-img" src="../static/img/mine_func_anquan.png"/>
					<label>用户名</label>
					<view class="right-text">test08</view>
				</view>
				<view class="list margintop10 borderBottom" @click="goRealName">
					<image class="icon-img" src="../static/img/mine_func_anquan.png"/>
					<label>实名认证</label>
					<view class="right-text">已完成</view>
				</view>
				<view class="list borderBottom" @click="goChangeLoginPassword">
					<image class="icon-img" src="../static/img/mine_func_anquan.png"/>
					<label>修改登录密码</label>
				</view>
				<view class="list" @click="goModifyPaymentPassword">
					<image class="icon-img" src="../static/img/mine_func_anquan.png"/>
					<label>修改支付密码</label>
				</view>
			</view>
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
			goRealName(){
				uni.navigateTo({
					url: "/pages/RealName",
				});
			},
			goModifyPaymentPassword(){
				uni.navigateTo({
					url: "/pages/ModifyPaymentPassword",
				});
			},
			goChangeLoginPassword(){
				uni.navigateTo({
					url: "/pages/ChangeLoginPassword",
				});
			},
		}
	}
</script>

<style scoped lang="scss">
.wrap{
	background-color: #f7f8fa;
	height: 100vh;
	.content{
		padding-top: 14upx;
		color: #557fab;
		.list{
			display: flex;
			padding: 20upx;
			background-color: #fff;
			font-size: 28upx;
			height: 52upx;
			align-items: center;
			.icon-img{
				width: 40upx;
				height: 40upx;
				margin-right: 20upx;
			}
			label{
				flex: 1;
			}
			.right-text{
				text-align: right;
			}
		}
	}
}
</style>
