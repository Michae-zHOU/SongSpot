const initialState = {
  id: 1,
  name: "抖音小王子",
  platforms: [
    {
      name: "tiktok",
      followers: 4000,
      likes: 2200,
      views: 339000,
    },
    {
      name: "bilibili",
      followers: 4000,
      likes: 2200,
      views: 339000,
    },
    {
      name: "iqiyi",
      followers: 4000,
      likes: 2200,
      views: 339000,
    },
  ],
  coins: 5,
};
export const CuratorData = (state = initialState, action) => {
  switch (action.type) {
  }
  return state;
};
