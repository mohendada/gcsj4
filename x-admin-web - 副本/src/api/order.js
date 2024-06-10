import request from '@/utils/request'

export function getOrderList(query) {
  return request({
    url: '/sys/order/GetAllOrder',
    method: 'get',
    params: {
      pageNum: query.pageNo,
      pageSize: query.pageSize,
      orderName: query.orderName
    }
  })
}

export function deleteOrder(id) {
  return request({
    url: `/sys/order/DeleteOrder`,
    method: 'delete',
    params: {id}
  })
}

export default {
  getOrderList,
  deleteOrder
}
