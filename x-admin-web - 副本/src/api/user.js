import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
export default {
  register(userform) {
    return request({
      url: '/user/register',
      method: 'post',
      data: userform
    })
  },
  loginuserform(userform) {
    return request({
      url: 'user/loginuserform',
      method: 'get',
      params: {
        username: userform.username,
        password: userform.password
      }
    })
  },
  forgotfomr(forgotfomr) {
    return request({
      url: 'user/forgotfomr',
      method: 'get',
      params: {
        userAccount: forgotfomr.userAccount,
        phoneNumber: forgotfomr.phoneNumber
      }
    })
  },
  forgotalter(forgotfomr) {
    alert(forgotfomr.userAccount)
    return request({
      url: 'user/forgotalter',
      method: 'put',
      data: forgotfomr
    })
  }
}

