<template>
	<view class="page">
		<van-nav-bar
			title="提现"
			left-arrow
			:border="false"
			fixed
			safe-area-inset-top
			@click-left="$base.BackPage('/pages/personal')"
			>
		</van-nav-bar>
		<view class="wrap">
			
			<van-dropdown-menu class="select">
				<van-dropdown-item v-model="value" :options="type" />
			</van-dropdown-menu>
			<view class="card-num">
				<view>USDT地址<label>12321312312312312</label></view>
				<!-- <view>银行卡 12312312321321321</view> -->
			</view>
			<view class="content">
				<view>提现金额</view>
				<view class="money">
					<label>¥</label>
					<input type="tel" placeholder="请输入提现金额" />
				</view>
				<view class="text">可提现金额319022元</view>
			</view>
			<view class="pay">
				<label>支付密码</label>
				<input type="tel" placeholder="请输入支付密码" />
			</view>
			<view class="btn-class">确认提现</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				type: [
					{ text: '提现到USDT钱包', value: 0 },
					{ text: '提现到银行卡', value: 1 },
				],
				value:0,
			}
		},
		methods: {
			// 返回上一页
			backPage(){
				window.history.back()
			}
		}
	}
</script>

<style scoped lang="scss">
.wrap{
	padding-top: 104upx;
	background-color: #f7f8fa;
	height: 100%;
	.select{
		margin-top: 20upx;
	}
	.card-num{
		width: 100%;
		height: 80upx;
		line-height: 80upx;
		padding-left: 15upx;
		background-color: #fff;
		margin-top: 20upx;
		label{
			margin-left: 20upx;
		}
	}
	.content{
		margin-top: 20upx;
		background-color: #fff;
		padding: 15upx;
		font-size: 24upx;
		.money{
			margin-top: 30upx;
			height: 50upx;
			display: flex;
			align-items: center;
			label{
				width: 80upx;
				font-size: 40upx;
				font-weight: 600;
				margin-left: 20upx;
			}
			input{
				font-size: 40upx;
			}
		}
		.text{
			font-size: 28upx;
			font-weight: 600;
			margin-top: 30upx;		
		}
	}
	.pay{
		margin-top: 30upx;
		height: 60upx;
		display: flex;
		align-items: center;
		background-color: #fff;
		padding: 10upx;
		margin-bottom: 66upx;
		label{
			width: 150upx;
			font-weight: 600;
			font-size: 28upx;
		}
	}
}
</style>
