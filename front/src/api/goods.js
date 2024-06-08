import request from '@/utils/request'

export function getGoodsList(query) {
  return request({
    url: '/sys/goods/GoodsList',
    method: 'get',
    params: {
      pageNum: query.pageNo,
      pageSize: query.pageSize,
      goodsName: query.goodsName,
      supplierId: query.supplierId
    }
  })
}

export function getGoodsById(id) {
  return request({
    url: `/sys/goods/ById`,
    method: 'get',
    params: {id}
  })
}

export function saveGoods(data, file) {
  const formData = new FormData();

  // 将数据对象的属性逐个添加到 FormData 中
  formData.append('goodsName', data.goodsName);
  formData.append('goodsPrice', data.goodsPrice);
  // formData.append('goodsPhoto', data.goodsPhoto);
  formData.append('supplierId', data.supplierId);
  // formData.append('goodsStatus', data.goodsStatus);

  // 添加文件对象到 FormData 中的 file 字段中
  formData.append('file', file);

  return request({
    url: data.goodsId ? '/sys/goods/Update' : '/sys/goods/Insert',
    method: data.goodsId ? 'put' : 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

export function deleteGoods(id) {
  return request({
    url: `/sys/goods/Delete`,
    method: 'delete',
    params: {id}
  })
}

export function changeGoodsStatus(id) {
  return request({
    url: '/sys/goods/goodStatus',
    method: 'post',
    params: {id}
  })
}

export default {
  getGoodsList,
  getGoodsById,
  saveGoods,
  deleteGoods,
  changeGoodsStatus
}
