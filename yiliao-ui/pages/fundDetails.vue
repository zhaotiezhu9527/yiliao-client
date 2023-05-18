<template>
	<view class="page">
		<van-nav-bar
			title="资金明细"
			left-arrow
			:border="false"
			fixed
			safe-area-inset-top
			@click-left="$base.BackPage('/pages/personal')"
			>
		</van-nav-bar>
		<view class="wrap">
			<table class="table-data">
				<tr>
					<th>摘要</th>
					<th class="table-money">金额</th>
					<th class="width-time">时间</th>
				</tr>
				<tr>
					<td>每日签到，获得奖励2元</td>
					<td class="width-small">+2</td>
					<td class="width-small">2023-05-18 12:28:10</td>
				</tr>
			</table>
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
	.table-data{
		width: 100%;
		border-collapse: collapse;
		font-size: 24upx;
		th,td{
			border-right: 1px solid #eee;
			border-bottom: 1px solid #eee;
		}
		.width-small{
			width: 20%;
		}
	}
}
</style>
