const initialState = {
  id: 0,
  name: "张艺兴",
  songs: [
    {
      name: "Rap nation",
      coverUrl: "",
      length: 10,
      url: "",
      approvals: 20,
      createdOn: "",
      issueDate: "",
      likes: 20,
      dislikes: 2,
    },
    {
      name: "北风吹",
      length: 20,
      coverUrl: "",
      url: "",
      approvals: 20,
      createdOn: "",
      issueDate: "",
      likes: 100,
      dislikes: 3,
    },
  ],
  coins: 1,
};
export const ArtistData = (state = initialState, action) => {
  switch (action.type) {
  }
  return state;
};
